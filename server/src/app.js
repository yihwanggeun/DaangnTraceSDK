// server/src/app.js
require('dotenv').config();  
const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');
const routes = require('./routes');
const { metricsMiddleware } = require('./metrics/prometheus');  // 추가

const app = express();
const port = process.env.PORT || 3000;

app.use(metricsMiddleware);

// URI가 제대로 로드되었는지 확인
console.log('MongoDB URI:', process.env.MONGODB_URI);

// MongoDB 연결
mongoose.connect(process.env.MONGODB_URI)
    .then(() => console.log('Successfully connected to MongoDB.'))
    .catch(err => {
        console.error('Error connecting to MongoDB:', err);
        process.exit(1);
    });

app.use(cors());
app.use(express.json());
app.use('/', routes);

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});