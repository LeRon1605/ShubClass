import jwt from 'jsonwebtoken'

class TokenProvider {
    async generateAuthCredential(account) {
        const access_token = jwt.sign({
            id: account.id,
            name: account.User.name,
            avatar: account.User.avatar,
            state: account.state,
            role: account.Role.name
        }, process.env.JWT_KEY, {
            expiresIn: 10 * 60 * 60
        });
        return {
            access_token
        };
    }
}

export default new TokenProvider();