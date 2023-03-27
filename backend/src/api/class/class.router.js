import express from 'express';
import ClassController from "./class.controller.js";
import { ValidationMiddleware } from '../../middlewares/index.js';
import { ClassCreateScheme, ClassUpdateScheme } from './dtos/index.js';
import { AuthorizationMiddleware } from '../../middlewares/index.js';
import APP_CONSTANT from '../../shared/app.constant.js';

const router = express.Router();

router.get('/', AuthorizationMiddleware({ type: 'basic' }),ClassController.getAllClasses)
      .post('/', AuthorizationMiddleware({ type:' role', value: 'Teacher' }), ValidationMiddleware(ClassCreateScheme, APP_CONSTANT.REQUEST_BODY), ClassController.createClass)
      .put('/:id', ValidationMiddleware(ClassUpdateScheme, APP_CONSTANT.REQUEST_BODY), ClassController.updateClass)
      .delete('/:id', ClassController.deleteClass);

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
 *           $ref: '#/definitions/Class'
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

export default router;