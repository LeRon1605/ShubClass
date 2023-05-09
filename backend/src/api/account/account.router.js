import express from 'express';
import AccountController from './account.controller.js';
import {
    ValidationMiddleware,
    AuthorizationMiddleware
} from '../../middlewares/index.js';
import { AccountCreateScheme, AccountUpdateScheme } from './dtos/index.js';
import APP_CONSTANT from '../../shared/app.constant.js';

const router = express.Router();

router
    .get('/', AccountController.getAllAccounts)
    .post(
        '/',
        ValidationMiddleware(AccountCreateScheme, APP_CONSTANT.REQUEST_BODY),
        AccountController.createAccount
    )
    .put(
        ':/id',
        ValidationMiddleware(AccountUpdateScheme, APP_CONSTANT.REQUEST_BODY),
        AccountController.updateAccount
    )
    .put(
        '/users:/id',
        ValidationMiddleware(AccountUpdateScheme, APP_CONSTANT.REQUEST_BODY),
        AccountController.updateUser
    )
    .post(
        '/change-password',
        AuthorizationMiddleware({ type: 'basic' }),
        AccountController.changePassword
    )
    .delete(':/id', AccountController.deleteAccount);

router.post('/login', AccountController.login);
router
    .get('/active', AccountController.activeAccount)
    .post('/active', AccountController.requestActiveMail);

router
    .post('/forget-password', AccountController.requestForgetPassword)
    .get('/forget-password/callback', AccountController.getForgetPasswordPage)
    .post('/forget-password/callback', AccountController.forgetPasswordHandler);

router.get(
    '/me',
    AuthorizationMiddleware({ type: 'basic' }),
    AccountController.sayValid
);

export default router;
