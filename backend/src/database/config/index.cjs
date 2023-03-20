const config = {
    development: {
        username: process.env.DB_USERNAME,
        password: process.env.DB_PASSWORD,
        database: process.env.DB_DBNAME,
        host: process.env.DB_HOST,
        dialect: 'mysql'
    }
};

module.exports = config;