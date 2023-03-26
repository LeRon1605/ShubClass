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

export default router;
