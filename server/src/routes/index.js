// server/src/routes/index.js
const express = require('express');
const router = express.Router();
const healthRoutes = require('./health');
const metricsRoutes = require('./metrics');  

router.use('/health', healthRoutes);
router.use('/metrics', metricsRoutes);  

module.exports = router;