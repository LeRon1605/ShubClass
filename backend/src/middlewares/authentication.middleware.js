import jwt from 'jsonwebtoken';
export default (req, res, next) => {
    if (req.headers.authorization) {
        const token =
            req.headers.authorization.split(' ')[1] ||
            req.headers.authorization;
        try {
            const decoded = jwt.verify(token, process.env.JWT_KEY);
            req.session = decoded;
        } catch (error) {
            req.session = null;
        }
    }
    next();
};
