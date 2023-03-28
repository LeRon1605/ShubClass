import redis from 'redis';

const instance = redis.createClient({
    url: process.env.REDIS_URL
});

export default instance;
