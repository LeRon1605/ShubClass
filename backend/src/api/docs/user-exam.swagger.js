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