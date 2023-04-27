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
 *  DoExamDto:
 *    type: object
 *    required:
 *      - email
 *      - password
 *    properties:
 *      examDetailId:
 *        type: string
 *      answer:
 *        type: string
 */

/**
 * @swagger
 * /user-exams/{id}/user-answers:
 *   post:
 *     tags: [Exam]
 *     description: Do exam
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: user exam id
 *         required: true
 *         in: path
 *         type: string
 *       - name: data
 *         description: user answer body
 *         required: true
 *         in: body
 *         schema:
 *          $ref: '#/definitions/DoExamDto' 
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *           $ref: '#/definitions/Error'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */