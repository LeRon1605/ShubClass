const handler = (req, res) => {
    return res.status(404).json({
        message: 'Not found.'
    });
};

export default handler;