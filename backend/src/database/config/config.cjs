const dotenv = require('dotenv');
dotenv.config({ path: '../../.env' });

module.exports = async () => ({
    development: {
        username: process.env.DB_USERNAME,
        password: process.env.DB_PASSWORD,
        database: process.env.DB_DBNAME,
        host: process.env.DB_HOST,
        dialect: 'mysql'
    },
    production: {
        username: process.env.DB_USERNAME,
        password: process.env.DB_PASSWORD,
        database: process.env.DB_DBNAME,
        host: process.env.DB_HOST,
        dialect: 'mysql'
    }
});