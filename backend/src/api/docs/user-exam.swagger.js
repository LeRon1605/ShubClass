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
 *
 */

/**
 * @swagger
 * /user-exams/{id}/submit:
 *   post:
 *     tags: [Exam]
 *     description: Submit exam
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: user exam id
 *         required: true
 *         in: path
 *         type: string
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

/**
 * @swagger
 * /user-exams/{id}:
 *   post:
 *     tags: [Exam]
 *     description: Start doing exam
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: exam id
 *         required: true
 *         in: path
 *         type: string
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *              type: object
 *              properties:
 *                  id:
 *                      type: string
 *                  userId:
 *                      type: string
 *                  examId:
 *                      type: string
 *                  startAt:
 *                      type: string
 *                      format: date-time
 *                      example: '2023-01-01T00:00:00Z'
 *                  endAt:
 *                      type: string
 *                      format: date-time
 *                      example: '2023-01-01T00:00:00Z'
 *                  userAnswers:
 *                      type: array
 *                      items:
 *                          type: object
 *                          properties:
 *                              userExamId:
 *                                  type: string
 *                              examDetailId:
 *                                  type: string
 *                              answer:
 *                                  type: string
 *       400:
 *         $ref: '#/responses/Error'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */