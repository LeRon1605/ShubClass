'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    await queryInterface.bulkInsert('student_class', [
      {
        studentId: '1',
        classId: '1',
        state: 1,
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ]);
    
    await queryInterface.bulkInsert('exams', [
      {
        id: '1',
        name: 'Bài test 1',
        type: 'Test',
        state: 2,
        'start-time': new Date(Date.now() + 7 * 3600 * 1000),
        'end-time': new Date(Date.now() + 8 * 3600 * 1000),
        classId: '1',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: '2',
        name: 'Bài test 2',
        type: 'Test',
        state: 2,
        'start-time': new Date(Date.now() + 7 * 3600 * 1000),
        'end-time': new Date(Date.now() + 8 * 3600 * 1000),
        classId: '1',
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ]);

    await queryInterface.bulkInsert('exam-details', [
      {
        id: '1',
        question: 'Who is he',
        answers: 'Bill|John|Kim|Lee',
        'true-answer': 'B',
        examId: '1',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: '2',
        question: 'Who is she',
        answers: 'Bill|John|Kim|Lee',
        'true-answer': 'B',
        examId: '1',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: '3',
        question: 'Who are they',
        answers: 'Bill|John|Kim|Lee',
        'true-answer': 'C',
        examId: '1',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: '4',
        question: 'What is it',
        answers: 'Pencil|John|Kim|Lee',
        'true-answer': 'D',
        examId: '1',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: '5',
        question: 'What are they',
        answers: 'Bill|John|Kim|Lee',
        'true-answer': 'B',
        examId: '1',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: '6',
        question: 'What are they',
        answers: 'Bill|John|Kim|Lee',
        'true-answer': 'B',
        examId: '2',
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ]);

    await queryInterface.bulkInsert('user-exams', [
      {
        id: '1',
        'start-at': new Date(),
        'end-at': new Date(),
        points: 7,
        examId: '1',
        studentId: '1',
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ]);
  },

  async down (queryInterface, Sequelize) {
    await queryInterface.bulkDelete('exams', {
      id: ['1', '2']
    });
    await queryInterface.bulkDelete('exam-details', {
      id: ['1', '2', '3', '4', '5', '6']
    });
    await queryInterface.bulkDelete('student_class', {
      studentId: '1',
      classId: '1'
    });
    await queryInterface.bulkDelete('user-exams', {
      id: ['1']
    });
  }
};
