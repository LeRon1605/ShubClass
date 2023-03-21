'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    await queryInterface.bulkInsert('roles', [
      {
        id: '1',
        name: 'Teacher',
        description: 'Giáo viên',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: '2',
        name: 'Student',
        description: 'Sinh viên',
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ]); 

    await queryInterface.bulkInsert('accounts', [
      {
        id: '1',
        email: 'ronle9519@gmail.com',
        password: '123qwe',
        state: 1,
        roleId: '1',
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: '2',
        email: 'hp_dut@gmail.com',
        password: '123qwe',
        state: 1,
        roleId: '2',
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ]);

    await queryInterface.bulkInsert('users', [
      {
        id: '1',
        name: 'Lê Quốc Rôn',
        avatar: 'empty',
        gender: true,
        createdAt: new Date(),
        updatedAt: new Date()
      },
      {
        id: '2',
        name: 'Đặng Hoài Phương',
        avatar: 'empty',
        gender: true,
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ]);

    await queryInterface.bulkInsert('classes', [
      {
        id: '1',
        name: 'Lập trình .NET',
        description: 'Lớp lập trình .NET 20Nh14',
        subject_name: 'Lập trình .NET',
        number_of_student: 40,
        teacherId: '2',
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ])
  },

  async down (queryInterface, Sequelize) {
    await queryInterface.bulkDelete('classes', {
      id: ['1']
    });
    await queryInterface.bulkDelete('users', {
      id: ['1', '2']
    });
    await queryInterface.bulkDelete('accounts', {
      id: ['1', '2']
    });
    await queryInterface.bulkDelete('roles', {
      id: ['1', '2']
    });
  }
};
