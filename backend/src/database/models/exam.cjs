'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Exam extends Model {
    static associate({ Class, ExamDetail, UserExam }) {
        Exam.belongsTo(Class, {
            foreignKey: 'classId'
        });

        Exam.hasMany(ExamDetail, {
            foreignKey: 'examId'
        });

        Exam.hasMany(UserExam, {
            foreignKey: 'examId'
        });
    }
  }
  Exam.init({
    id: {
        type: DataTypes.STRING,
        primaryKey: true,
        field: 'id'
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'name'
    },
    type: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'type'
    },
    startTime: {
        type: DataTypes.DATE,
        allowNull: false,
        field: 'start-time'
    },
    endTime: {
        type: DataTypes.DATE,
        allowNull: false,
        field: 'end-time'
    },
    classId: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'classId'
    }
  }, {
    sequelize,
    modelName: 'Exam',
    tableName: 'exams'
  });
  return Exam;
};