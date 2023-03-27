import express from "express";
import AccountController from "./account.controller.js";
import { ValidationMiddleware } from "../../middlewares/index.js";
import { AccountCreateScheme, AccountUpdateScheme } from "./dtos/index.js";
import APP_CONSTANT from "../../shared/app.constant.js";

const router = express.Router();

router
  .get("/", AccountController.getAllAccountsOfRole)
  .post(
    "/",
    ValidationMiddleware(AccountCreateScheme, APP_CONSTANT.REQUEST_BODY),
    AccountController.createAccount
  )
  .put(
    ":/id",
    ValidationMiddleware(AccountUpdateScheme, APP_CONSTANT.REQUEST_BODY),
    AccountController.updateAccount
  )
  .delete(":/id", AccountController.deleteAccount);

router.post('/login', AccountController.login);

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

export default router;
