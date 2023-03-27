import redis from 'redis';

class CacheService {
    constructor() {
        this.instance = redis.createClient({
            host: process.env.REDIS_HOST,
            port: process.env.REDIS_PORT
        });
    }

    async set(key, value, expire) {
        await this.instance.connect();
        await this.instance.set(key, JSON.stringify(value));
        await this.instance.disconnect();
    }

    async get(key) {
        await this.instance.connect();
        const value = await this.instance.get(key);
        await this.instance.disconnect();
        if (value != null) {
            return JSON.parse(value);
        }
        return null;
    }

    async remove(key) {
        await this.instance.connect();
        await this.instance.del(key);
        await this.instance.disconnect();
    }
}

export default new CacheService();