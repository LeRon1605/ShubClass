import HttpException from '../shared/exceptions/base/Http.exception.js';

const handler = (error, req, res, next) => {
    console.error(`[Error]: ${error}`);
    if (error.statusCode && error.statusCode != 500) {
        return res.status(error.statusCode).json({
            message: error.message
        });
    } else {
        return res.status(500).json({
            message: error.message
        });
    }
};

export default handler;
