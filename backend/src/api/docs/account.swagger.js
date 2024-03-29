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
 *      - message
 *    properties:
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
 *  UserInfoDto:
 *    type: object
 *    properties:
 *      name:
 *          type: string
 *      email:
 *          type: string
 *      dateOfBirth:
 *          type: string
 *          format: date-time
 *          example: '2023-01-01T00:00:00Z'
 *      school:
 *          type: string
 *      grade:
 *          type: number
 *      phoneNumber:
 *          type: string
 *      address:
 *          type: string
 *      gender:
 *          type: boolean
 *  AccountUpdateDto:
 *    type: object
 *    properties:
 *      name:
 *          type: string
 *      dateOfBirth:
 *          type: string
 *          format: date-time
 *          example: '2023-01-01T00:00:00Z'
 *      school:
 *          type: string
 *      grade:
 *          type: number
 *      phoneNumber:
 *          type: string
 *      address:
 *          type: string
 *      gender:
 *          type: boolean
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
 * /accounts/me:
 *   get:
 *     tags: [Auth]
 *     description: Get current login user info
 *     produces:
 *       - application/json
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *          $ref: '#/definitions/UserInfoDto'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 * /accounts:
 *   put:
 *     tags: [Auth]
 *     description: Update user info
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: data
 *         description: account information
 *         required: true
 *         in: body
 *         schema:
 *           $ref: '#/definitions/AccountUpdateDto'
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *          $ref: '#/definitions/UserInfoDto'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */