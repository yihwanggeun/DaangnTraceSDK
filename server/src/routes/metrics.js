// server/src/routes/mertics.js
const express = require('express');
const router = express.Router();
const Event = require('../model/Event');

router.post('/events', async (req, res) => {
    try {
        await Event.insertMany(req.body);
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