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
 *  ExamCreateDto:
 *      type: object
 *      required:
 *          - type
 *          - name
 *          - startTime
 *          - endTime
 *          - details
 *          - classId
 *      properties:
 *          type:
 *              type: string
 *          name:
 *              type: string
 *          startTime:
 *              type: string
 *              format: date-time
 *              example: '2023-01-01T00:00:00Z'
 *          endTime:
 *              type: string
 *              format: date-time
 *              example: '2023-01-01T00:00:00Z'
 *          details:
 *              type: string
 *          classId:
 *              type: string
 *  ExamDto:
 *      type: object
 *      properties:
 *          id:
 *              type: string
 *          state:
 *              type: number
 *          type:
 *              type: string
 *          name:
 *              type: string
 *          startTime:
 *              type: string
 *              format: date-time
 *              example: '2023-01-01T00:00:00Z'
 *          endTime:
 *              type: string
 *              format: date-time
 *              example: '2023-01-01T00:00:00Z'
 *          details:
 *              type: string
 *          classId:
 *              type: string
 *  QuestionDto:
 *      type: object
 *      properties:
 *          id:
 *              type: string
 *          question:
 *              type: string
 *          answers:
 *              type: array
 *              items:
 *                  type: string
 *  ExamResultDto:
 *      type: object
 *      properties:
 *          exam:
 *              type: object
 *              properties:
 *                  id:
 *                      type: string
 *                  name:
 *                      type: string
 *                  startTime:
 *                      type: string
 *                      format: date-time
 *                      example: '2023-01-01T00:00:00Z'
 *                  endTime:
 *                      type: string
 *                      format: date-time
 *                      example: '2023-01-01T00:00:00Z'
 *          user:
 *              type: object
 *              properties:
 *                  id:
 *                      type: string
 *                  name:
 *                      type: string
 *                  avatar:
 *                      type: string
 *          points:
 *              type: number
 *          startAt:
 *              type: string
 *              format: date-time
 *              example: '2023-01-01T00:00:00Z'
 *          endAt:
 *              type: string
 *              format: date-time
 *              example: '2023-01-01T00:00:00Z'
 */

/**
 * @swagger
 * /exams:
 *   post:
 *     tags: [Exam]
 *     description: Teacher create new exam
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: data
 *         description: exam information
 *         required: true
 *         in: body
 *         schema:
 *           $ref: '#/definitions/ExamCreateDto'
 *     responses:
 *       200:
 *         $ref: '#/responses/Success'
 *       400:
 *         $ref: '#/responses/Error'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 * /exams/{id}:
 *   delete:
 *     tags: [Exam]
 *     description: Teacher delete exam
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
 *         $ref: '#/responses/Success'
 *       400:
 *         $ref: '#/responses/Error'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 * /classes/{id}/exams:
 *   get:
 *     tags: [Class]
 *     description: Get all exams in class
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: class id
 *         required: true
 *         in: path
 *         type: string
 *     responses:
 *       200:
 *          description: Ok
 *          schema:
 *              type: array
 *              items:
 *                  $ref: '#/definitions/ExamDto'
 *       400:
 *         $ref: '#/responses/Error'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 * /exams/{id}/questions:
 *   get:
 *     tags: [Exam]
 *     description: Get all question in exam
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
 *          description: Ok
 *          schema:
 *              type: array
 *              items:
 *                  $ref: '#/definitions/QuestionDto'
 *       400:
 *         $ref: '#/responses/Error'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 * /exams/{id}/result:
 *   get:
 *     tags: [Exam]
 *     description: Get result of exam
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
 *          description: Ok
 *          schema:
 *              $ref: '#/definitions/ExamResultDto'
 *       400:
 *         $ref: '#/responses/Error'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 * /exams/{id}/result/{studentId}:
 *   get:
 *     tags: [Exam]
 *     description: Get result of exam for specific user
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: exam id
 *         required: true
 *         in: path
 *         type: string
 *       - name: studentId
 *         description: student id
 *         required: true
 *         in: path
 *         type: string
 *     responses:
 *       200:
 *          description: Ok
 *          schema:
 *              $ref: '#/definition/ExamResultDto'
 *       400:
 *         $ref: '#/responses/Error'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */
