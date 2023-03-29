const dotenv = require('dotenv');
dotenv.config({ path: '../../.env' });

module.exports = async () => ({
    development: {
        username: process.env.DB_USERNAME,
        password: process.env.DB_PASSWORD,
        database: process.env.DB_DBNAME,
        host: process.env.DB_HOST,
        port: process.env.DB_PORT,
        logging: false,
        dialect: 'mysql',
        charset: 'utf8mb4',
        collate: 'utf8mb4_unicode_ci'
    },
    production: {
        username: process.env.DB_USERNAME,
        password: process.env.DB_PASSWORD,
        database: process.env.DB_DBNAME,
        host: process.env.DB_HOST,
        port: process.env.DB_PORT,
        logging: false,
        dialect: 'mysql',
        charset: 'utf8mb4',
        collate: 'utf8mb4_unicode_ci'
    }
});