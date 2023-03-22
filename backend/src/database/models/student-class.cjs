'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class StudentClass extends Model {
    static associate({ User, Class }) {
        StudentClass.belongsTo(User, {
            foreignKey: 'studentId'
        });

        StudentClass.belongsTo(Class, {
            foreignKey: 'classId'
        });
    }
  }
  StudentClass.init({
    studentId: {
        type: DataTypes.STRING,
        primaryKey: true,
        field: 'studentId'
    },
    classId: {
        type: DataTypes.STRING,
        primaryKey: true,
        field: 'classId'
    },
    state: {
        type: DataTypes.STRING,
        primaryKey: true,
        field: 'state'
    }
  }, {
    sequelize,
    modelName: 'StudentClass',
    tableName: 'student_class'
  });
  return StudentClass;
};