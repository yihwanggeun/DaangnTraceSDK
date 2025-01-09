// server/src/model/Event.js
const mongoose = require('mongoose');

const eventSchema = new mongoose.Schema({
    type: { 
        type: String, 
        required: true 
    },
    timestamp: { 
        type: Number, 
        required: true 
    },
    metadata: { 
        type: Map,
        of: mongoose.Schema.Types.Mixed 
    },
    processedAt: { 
        type: Date, 
        default: Date.now 
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Event', eventSchema);