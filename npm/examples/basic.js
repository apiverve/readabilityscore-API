/**
 * Basic Example - Text Readability Score API
 *
 * This example demonstrates how to use the Text Readability Score API.
 * Make sure to set your API key in the .env file or replace '[YOUR_API_KEY]' below.
 */

require('dotenv').config();
const readabilityscoreAPI = require('../index.js');

// Initialize the API client
const api = new readabilityscoreAPI({
    api_key: process.env.API_KEY || '[YOUR_API_KEY]'
});

// Example query
var query = {
  "text": "Western astrology is founded on the movements and relative positions of celestial bodies such as the Sun, Moon and planets, which are analysed by their movement through signs of the zodiac (twelve spatial divisions of the ecliptic) and by their aspects (based on geometric angles) relative to one another."
};

// Make the API request using callback
console.log('Making request to Text Readability Score API...\n');

api.execute(query, function (error, data) {
    if (error) {
        console.error('Error occurred:');
        if (error.error) {
            console.error('Message:', error.error);
            console.error('Status:', error.status);
        } else {
            console.error(JSON.stringify(error, null, 2));
        }
        return;
    }

    console.log('Response:');
    console.log(JSON.stringify(data, null, 2));
});
