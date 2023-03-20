'use strict';
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    // Table
    await queryInterface.createTable('accounts', {
      id: {
        type: Sequelize.STRING,
        primaryKey: true,
        field: 'id'
      },
      email: {
          type: Sequelize.STRING,
          allowNull: false,
          field: 'email'
      },
      password: {
          type: Sequelize.STRING,
          allowNull: false,
          field: 'password'
      },
      state: {
          type: Sequelize.INTEGER,
          allowNull: false,
          field: 'state'
      },
      roleId: {
        type: Sequelize.STRING,
        allowNull: false,
        field: 'roleId'
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

    await queryInterface.createTable('users', {
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
      dateOfBirth: {
        type: Sequelize.DATE,
        allowNull: true,
        field: 'data_of_birth'
      },
      school: {
        type: Sequelize.STRING,
        allowNull: true,
        field: 'school'
      },
      grade: {
        type: Sequelize.INTEGER,
        allowNull: true,
        field: 'grade'
      },
      phoneNumber: {
        type: Sequelize.STRING,
        allowNull: true,
        field: 'phone_number'
      },
      address: {
        type: Sequelize.STRING,
        allowNull: true,
        field: 'address'
      },
      gender: {
        type: Sequelize.BOOLEAN,
        allowNull: false,
        field: 'gender'
      },
      avatar: {
        type: Sequelize.STRING,
        allowNull: false,
        field: 'avatar'
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

    await queryInterface.createTable('roles', {
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
      description: {
        type: Sequelize.STRING,
        allowNull: false,
        field: 'description'
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

    await queryInterface.createTable('classes', {
      id: {
        type: Sequelize.STRING,
        primaryKey: true,
        onDelete: 'CASCADE',
        field: 'id'
      },
      name: {
        type: Sequelize.STRING,
        allowNull: false,
        field: 'name'
      },
      description: {
        type: Sequelize.STRING,
        allowNull: false,
        field: 'description'
      },
      subjectName: {
        type: Sequelize.STRING,
        allowNull: false,
        field: 'subject_name'
      },
      numberOfStudent: {
        type: Sequelize.INTEGER,
        allowNull: false,
        field: 'number_of_student'
      },
      teacherId: {
        type: Sequelize.STRING,
        allowNull: false,
        field: 'teacherId'
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

    await queryInterface.createTable('student_class', {
      studentId: {
        type: Sequelize.STRING,
        primaryKey: true,
        field: 'studentId'
      },
      classId: {
          type: Sequelize.STRING,
          primaryKey: true,
          field: 'classId'
      },
      state: {
          type: Sequelize.STRING,
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

    // Constraint
    await queryInterface.addConstraint('users', {
      type: 'foreign key',
      name: 'fk_user_account',
      fields: ['id'],
      references: {
        table: 'accounts',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });

    await queryInterface.addConstraint('accounts', {
      type: 'foreign key',
      name: 'fk_account_role',
      fields: ['roleId'],
      references: {
        table: 'roles',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });

    await queryInterface.addConstraint('classes', {
      type: 'foreign key',
      name: 'fk_class_teacher',
      fields: ['teacherId'],
      references: {
        table: 'users',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });

    await queryInterface.addConstraint('student_class', {
      type: 'foreign key',
      name: 'fk_class',
      fields: ['classId'],
      references: {
        table: 'classes',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });

    await queryInterface.addConstraint('student_class', {
      type: 'foreign key',
      name: 'fk_student',
      fields: ['studentId'],
      references: {
        table: 'users',
        field: 'id'
      },
      onDelete: 'CASCADE',
      onUpdate: 'CASCADE'
    });
  },
  async down(queryInterface, Sequelize) {
    await queryInterface.removeConstraint('student_class', 'fk_student');
    await queryInterface.removeConstraint('student_class', 'fk_class');
    await queryInterface.removeConstraint('classes', 'fk_class_teacher');
    await queryInterface.removeConstraint('users', 'fk_user_account');
    await queryInterface.removeConstraint('accounts', 'fk_account_role');
    await queryInterface.dropTable('users');

    await queryInterface.dropTable('accounts');

    await queryInterface.dropTable('roles');
  }
};