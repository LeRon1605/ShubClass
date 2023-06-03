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
 *  Forbidden:
 *    description: Not allowed to access resouce
 *    schema:
 *      $ref: '#/definitions/Error'
 *  Success:
 *    description: Success with message
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
 *  Class:
 *    type: object
 *    properties:
 *      id:
 *        type: string
 *      name:
 *        type: string
 *      description:
 *        type: string
 *      subjectName:
 *        type: string
 *      numberOfStudent:
 *        type: number
 *      teacherId:
 *        type: string
 *      createdAt:
 *        type: string
 *        format: date-time
 *        example: '2023-01-01T00:00:00Z'
 *      updatedAt:
 *        type: string
 *        format: date-time
 *        example: '2023-01-01T00:00:00Z'
 *
 *  ClassCreate:
 *    type: object
 *    properties:
 *      id:
 *        type: string
 *      name:
 *        type: string
 *      description:
 *        type: string
 *      subjectName:
 *        type: string
 *      numberOfStudent:
 *        type: number
 *
 *  ClassUpdate:
 *    type: object
 *    properties:
 *      name:
 *        type: string
 *      description:
 *        type: string
 *      subjectName:
 *        type: string
 *      numberOfStudent:
 *        type: number
 *
 *  Student:
 *    type: object
 *    properties:
 *      id:
 *          type: string
 *      name:
 *          type: string
 *      phoneNumber:
 *          type: string
 *      gender:
 *          type: boolean
 *      avatar:
 *          type: string
 *
 *  Request:
 *    type: object
 *    properties:
 *      state:
 *          type: string
 *      student:
 *          $ref: '#/definitions/Student'
 *  StudentSummaryDto:
 *    type: object
 *    properties:
 *      average:
 *          type: number
 *      completedExam:
 *          type: number
 *      numberOfExams:
 *          type: number
 */

/**
 * @swagger
 *
 * /classes:
 *   get:
 *     tags: [Class]
 *     description: Get all classes that student has joined or classes have been created by teacher
 *     produces:
 *       - application/json
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *              type: array
 *              items:
 *                  $ref: '#/definitions/Class'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes:
 *   post:
 *     tags: [Class]
 *     description: Teacher create new class
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: data
 *         description: class information
 *         required: true
 *         in: body
 *         schema:
 *           $ref: '#/definitions/ClassCreate'
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *           $ref: '#/definitions/Class'
 *       400:
 *         $ref: '#/responses/Error'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/search:
 *   get:
 *     tags: [Class]
 *     description: Search class by id
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         description: class id
 *         required: true
 *         in: query
 *         type: string
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *              type: array
 *              items:
 *                  $ref: '#/definitions/Class'
 *       400:
 *         $ref: '#/responses/Error'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/{id}:
 *   put:
 *     tags: [Class]
 *     description: Teacher update class information
 *     produces:
 *       - application/json
 *     security:
 *       - BearerAuth: []
 *     parameters:
 *       - name: id
 *         description: class id
 *         required: true
 *         in: path
 *         type: string
 *       - name: data
 *         description: class information
 *         required: true
 *         in: body
 *         schema:
 *           $ref: '#/definitions/ClassUpdate'
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *           $ref: '#/definitions/Class'
 *       400:
 *         $ref: '#/responses/Error'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/{id}:
 *   delete:
 *     tags: [Class]
 *     description: Teacher delete class
 *     produces:
 *       - application/json
 *     security:
 *       - BearerAuth: []
 *     parameters:
 *       - name: id
 *         description: class id
 *         required: true
 *         in: path
 *         type: string
 *     responses:
 *       200:
 *         description: OK
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/{id}/students:
 *   get:
 *     tags: [Class]
 *     description: Get all students in class
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
 *         description: OK
 *         schema:
 *              type: array
 *              items:
 *                  $ref: '#/definitions/Student'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/{id}/students:
 *   delete:
 *     tags: [Class]
 *     description: Exit class
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
 *         description: OK
 *         schema:
 *              type: array
 *              items:
 *                  $ref: '#/definitions/Student'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */


/**
 * @swagger
 *
 * /classes/{id}/students/{studentId}:
 *   delete:
 *     tags: [Class]
 *     description: Remove student from class
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         in: path
 *         description: class id
 *         required: true
 *         type: string
 *       - name: studentId
 *         in: path
 *         description: student id
 *         required: true
 *         type: string
 *     responses:
 *       200:
 *         $ref: '#/responses/Success'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/{id}/requests:
 *   get:
 *     tags: [Request]
 *     description: Teacher get all request of student to class
 *     produces:
 *       - application/json
 *     parameters:
 *       - in: path
 *         name: id
 *         description: class id
 *         required: true
 *         type: string
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *           $ref: '#/definitions/Request'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/{id}/requests:
 *   post:
 *     tags: [Request]
 *     description: Student make request class
 *     produces:
 *       - application/json
 *     parameters:
 *       - in: path
 *         name: id
 *         description: class id
 *         required: true
 *         type: string
 *     responses:
 *       200:
 *         $ref: '#/responses/Success'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/{id}/requests/{studentId}:
 *   put:
 *     tags: [Request]
 *     description: Approve student request
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         in: path
 *         description: class id
 *         required: true
 *         type: string
 *       - name: studentId
 *         in: path
 *         description: student id
 *         required: true
 *         type: string
 *     responses:
 *       200:
 *         $ref: '#/responses/Success'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/{id}/requests/{studentId}:
 *   delete:
 *     tags: [Request]
 *     description: Reject student request
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         in: path
 *         description: class id
 *         required: true
 *         type: string
 *       - name: studentId
 *         in: path
 *         description: student id
 *         required: true
 *         type: string
 *     responses:
 *       200:
 *         $ref: '#/responses/Success'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/{id}/students/me:
 *   get:
 *     tags: [Class]
 *     description: Get summary information about you in class
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         in: path
 *         description: class id
 *         required: true
 *         type: string
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *            $ref: '#/definitions/StudentSummaryDto'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */

/**
 * @swagger
 *
 * /classes/{id}:
 *   get:
 *     tags: [Class]
 *     description: Get class information
 *     produces:
 *       - application/json
 *     parameters:
 *       - name: id
 *         in: path
 *         description: class id
 *         required: true
 *         type: string
 *     responses:
 *       200:
 *         description: OK
 *         schema:
 *            $ref: '#/definitions/Class'
 *       401:
 *         $ref: '#/responses/Unauthorized'
 *       404:
 *         $ref: '#/responses/NotFound'
 *       403:
 *         $ref: '#/responses/Forbidden'
 *       500:
 *         $ref: '#/responses/InternalServerError'
 */
