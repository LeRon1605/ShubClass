import { UnauthorizedException } from '../shared/exceptions/index.js';
export default (config) => {
    return (req, res, next) => {
        const { type, value } = config;
        if (type.toLowerCase() == 'basic') {
            if (req.session) {
                next();
            } else {
                throw new UnauthorizedException('You are not log in.');
            }
        } else if (type.toLowerCase() == 'role') {
            if (req.session) {
                if (req.session.role != value) {
                    throw new UnauthorizedException(
                        'You are not permit to do this action.'
                    );
                } else {
                    next();
                }
            } else {
                throw new UnauthorizedException('You are not log in.');
            }
        } else {
            next();
        }
    };
};
