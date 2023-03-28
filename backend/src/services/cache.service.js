import redis from '../shared/config/redis.config.js';

class CacheService {
    async set(key, value, expire) {
        await redis.connect();
        await redis.set(key, JSON.stringify(value), {
            EX: expire
        });
        await redis.quit();
    }

    async get(key) {
        await redis.connect();
        const value = await redis.get(key);
        await redis.quit();
        if (value != null) {
            return JSON.parse(value);
        }
        return null;
    }

    async remove(key) {
        await redis.connect();
        await redis.del(key);
        await redis.quit();
    }
}

export default new CacheService();
