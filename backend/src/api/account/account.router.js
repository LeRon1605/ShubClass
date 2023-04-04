import express from 'express';
import AccountController from './account.controller.js';
import { ValidationMiddleware, AuthorizationMiddleware } from '../../middlewares/index.js';
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
    .post('/change-password', AuthorizationMiddleware({ type: 'basic' }), AccountController.changePassword)
    .delete(':/id', AccountController.deleteAccount);

router.post('/login', AccountController.login);
router
    .get('/active', AccountController.activeAccount)
    .post('/active', AccountController.requestActiveMail);

router.post('/forget-password', AccountController.requestForgetPassword)
    .get('/forget-password/callback', AccountController.getForgetPasswordPage)
    .post('/forget-password/callback', AccountController.forgetPasswordHandler);

/**
 * @swagger
 *
 *responses:
 *  Error:
 *    description: Bad request
 *    schema:
 *      $ref: '#/definitions/Error'
 *  Unauthorized:
 *    description: Unauthorized
 *    schema:
 *      $ref: '#/definitions/Error'
 *  NotFound:
 *    description: The specified resource was not found
 *    schema:
 *      $ref: '#/definitions/Error'
 *  InternalServerError:
 *    description: Internal server error
 *    schema:
 *      $ref: '#/definitions/Error'
 *
 * definitions:
 *  Error:
 *    type: object
 *    required:
 *      - code
 *      - message
 *    properties:
 *      code:
 *        type: string
 *      message:
 *        type: string
 *  Login:
 *    type: object
 *    required:
 *      - email
 *      - password
 *    properties:
 *      email:
 *        type: string
 *      message:
 *        type: string
 *  Register:
 *    type: object
 *    required:
 *      - email
 *      - password
 *      - name
 *    properties:
 *      email:
 *        type: string
 *      password:
 *        type: string
 *      name:
 *        type: string
 *      dateOfBirth:
 *        type: string
 *        format: date-time
 *        example: '2023-01-01T00:00:00Z'
 *      school:
 *        type: string
 *      grade:
 *        type: number
 *      phoneNumber:
 *        type: string
 *      address:
 *        type: string
 *      gender:
 *        type: boolean
 *      role:
 *        type: string
 *  AuthCredential:
 *    type: object
 *    required:
 *      - access_token
 *    properties:
 *      access_token:
 *        type: string
 */

/**
 * @swagger
 * /accounts/login:
 *   post:
 *     tags: [Auth]
 *     description: Login
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: data
 *         description: account information
 *         required: true
 *         in: body
 *         schema:
 *           type: object
 *           properties:
 *            email:
 *              type: string
 *            password:
 *              type: string
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *           $ref: '#/definitions/AuthCredential'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 * /accounts:
 *   post:
 *     tags: [Auth]
 *     description: Register
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: data
 *         description: account information
 *         required: true
 *         in: body
 *         schema:
 *           $ref: '#/definitions/Register'
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *            type: object
 *            properties:
 *              message:
 *                type: string
 *       400:
 *         $ref: '#/responses/Error'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 * /accounts/active:
 *   post:
 *     tags: [Auth]
 *     description: Request mail active account
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: data
 *         description: account information
 *         required: true
 *         in: body
 *         schema:
 *           type: object
 *           properties:
 *              email:
 *                type: string
 *     responses:
 *       200:
 *         description: OK
 *       400:
 *         $ref: '#/responses/Error'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 * /accounts/forget-password:
 *   post:
 *     tags: [Auth]
 *     description: Request forget password mail
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: data
 *         description: email
 *         required: true
 *         in: body
 *         schema:
 *           type: object
 *           properties:
 *              email:
 *                type: string
 *     responses:
 *       200:
 *         description: OK
 *       404:
 *         $ref: '#/responses/NotFound'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

export default router;
