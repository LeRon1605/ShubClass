'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    await queryInterface.createTable('exams', {
      id: {
        type: Sequelize.STRING,
        primaryKey: true,
        field: 'id'
      },
      name: {
        type: Sequelize.STRING,
        allowNull: false,
        field: 'name'
      },
      type: {
          type: Sequelize.STRING,
          allowNull: false,
          field: 'type'
      },
      state: {
          type: Sequelize.INTEGER,
          allowNull: false,
          field: 'state'
      },
      startTime: {
          type: Sequelize.DATE,
          allowNull: false,
          field: 'start-time'
      },
      endTime: {
          type: Sequelize.DATE,
          allowNull: false,
          field: 'end-time'
      },
      classId: {
          type: Sequelize.STRING,
          allowNull: false,
          field: 'classId'
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });

    await queryInterface.createTable('exam-details', {
      id: {
        type: Sequelize.STRING,
        primaryKey: true,
        field: 'id'
      },
      question: {
        type: Sequelize.STRING,
        allowNull: false,
        field: 'question'
      },
      answers: {
          type: Sequelize.STRING,
          allowNull: false,
          field: 'answers'
      },
      trueAnswer: {
          type: Sequelize.STRING,
          allowNull: false,
          field: 'true-answer'
      },
      examId: {
          type: Sequelize.STRING,
          allowNull: false,
          field: 'examId'
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });

    await queryInterface.createTable('user-exams', {
      id: {
        type: Sequelize.STRING,
        primaryKey: true,
        field: 'id'
      },
      startAt: {
        type: Sequelize.DATE,
        allowNull: false,
        field: 'start-at'
      },
      endAt: {
          type: Sequelize.DATE,
          allowNull: true,
          field: 'end-at'
      },
      points: {
          type: Sequelize.INTEGER,
          allowNull: true,
          field: 'points'
      },
      examId: {
          type: Sequelize.STRING,
          allowNull: false,
          field: 'examId'
      },
      studentId: {
          type: Sequelize.STRING,
          allowNull: false,
          field: 'studentId'
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });

    await queryInterface.createTable('user-answers', {
      userExamId: {
        type: Sequelize.STRING,
        allowNull: false,
        primaryKey: true,
        field: 'userExamId'
      },
      examDetailId: {
          type: Sequelize.STRING,
          allowNull: false,
          primaryKey: true,
          field: 'examDetailId'
      },
      userAnswer: {
          type: Sequelize.STRING,
          allowNull: false,
          field: 'user-answer'
      },
      state: {
          type: Sequelize.BOOLEAN,
          allowNull: false,
          field: 'state'
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });

    await queryInterface.addConstraint('exams', {
      type: 'foreign key',
      name: 'fk_exam_class',
      fields: ['classId'],
      references: {
        table: 'classes',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });

    await queryInterface.addConstraint('exam-details', {
      type: 'foreign key',
      name: 'fk_exam_detail',
      fields: ['examId'],
      references: {
        table: 'exams',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });

    await queryInterface.addConstraint('user-exams', {
      type: 'foreign key',
      name: 'fk_exam',
      fields: ['examId'],
      references: {
        table: 'exams',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });

    await queryInterface.addConstraint('user-exams', {
      type: 'foreign key',
      name: 'fk_exam_student',
      fields: ['studentId'],
      references: {
        table: 'users',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });

    await queryInterface.addConstraint('user-answers', {
      type: 'foreign key',
      name: 'fk_userExam',
      fields: ['userExamId'],
      references: {
        table: 'user-exams',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });

    await queryInterface.addConstraint('user-answers', {
      type: 'foreign key',
      name: 'fk_examDetail',
      fields: ['examDetailId'],
      references: {
        table: 'exam-details',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });
  },

  async down (queryInterface, Sequelize) {
    await queryInterface.removeConstraint('exams', 'fk_exam_class');
    await queryInterface.removeConstraint('exam-details', 'fk_exam_detail');
    await queryInterface.removeConstraint('user-exams', 'fk_exam');
    await queryInterface.removeConstraint('user-exams', 'fk_exam_student');
    await queryInterface.removeConstraint('user-answers', 'fk_userExam');
    await queryInterface.removeConstraint('user-answers', 'fk_examDetail');

    await queryInterface.dropTable('exams');
    await queryInterface.dropTable('exam-details');
    await queryInterface.dropTable('user-exams');
    await queryInterface.dropTable('user-answers');
  }
};
