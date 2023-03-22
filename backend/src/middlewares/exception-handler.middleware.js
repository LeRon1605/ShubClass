import HttpException from '../exceptions/base/Http.exception.js';

const handler = (error, req, res, next) => {
    if (error.statusCode && error.statusCode != 500) {
        return res.status(error.statusCode).json({
            message: error.message
        });
    } else {
        return res.status(500).json({
            message: error.message
        })
    }
};

export default handler;