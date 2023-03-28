export default (scheme, source) => {
    return (req, res, next) => {
        const result = scheme.validate(req[source]);
        if (result.error) {
            const responseMessage = result.error.details.reduce(
                (result, element) => {
                    result.details[element.path[0]] = element.message;
                    return result;
                },
                {
                    message: 'Validation error.',
                    details: {}
                }
            );
            return res.status(400).json(responseMessage);
        } else {
            next();
        }
    };
};
