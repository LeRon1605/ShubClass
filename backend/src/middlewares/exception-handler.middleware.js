import HttpException from '../exceptions/base/http.exception.js';

const handler = (req, res, next) => {
    try {
        next(req);
    } catch (error) {
        if (error instanceof HttpException) {
            return res.json(error.statusCode).json(message);
        }
        return res.status(500).json({
            message: 'Interval server error'
        });
    }
};

export default handler;