// server/src/routes/health.js
const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');

router.get('/', async (req, res) => {
    try {
        if (mongoose.connection.readyState === 1) {
            res.status(200).json({ 
                status: 'ok',
                database: 'connected'
            });
        } else {
            res.status(503).json({ 
                status: 'error',
                database: 'disconnected'
            });
        }
    } catch (error) {
        res.status(500).json({ status: 'error' });
    }
});

module.exports = router;