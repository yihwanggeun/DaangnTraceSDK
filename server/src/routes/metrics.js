// server/src/routes/metrics.js
const express = require('express');
const router = express.Router();
const Event = require('../model/Event');
const {
   totalButtonClicks,
   buttonClicksByIdCounter, 
   buttonClickLatency
} = require('../metrics/prometheus');

router.post('/events', async (req, res) => {
   const startTime = process.hrtime();
   
   try {
       await Event.insertMany(req.body);
       
       // 버튼 클릭 메트릭 기록
       req.body.forEach(event => {
           if (event.type === 'BUTTON_CLICK') {
               const buttonId = event.metadata?.buttonId;
               if (buttonId) {
                   totalButtonClicks.inc();
                   buttonClicksByIdCounter.inc({ buttonId });
               }
           }
       });
       
       // 처리 시간 기록 
       const [seconds, nanoseconds] = process.hrtime(startTime);
       const duration = seconds + nanoseconds / 1e9;
       buttonClickLatency.observe({
           type: 'batch',
           buttonId: 'all'  
       }, duration);

       res.status(200).json({
           message: 'Events saved',
           count: req.body.length,
           timestamp: new Date().toISOString()
       });
   } catch (error) {
       console.error('Save Error:', error);
       res.status(500).json({ error: 'Failed to save events' });
   }
});

router.get('/events', async (req, res) => {
   try {
       const events = await Event.find().sort({ timestamp: -1 });
       res.status(200).json(events);
   } catch (error) {
       console.error('Fetch Error:', error);
       res.status(500).json({ error: 'Failed to fetch events' });
   }
});

module.exports = router;