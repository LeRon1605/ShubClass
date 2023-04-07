'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class ExamDetail extends Model {
    static associate({ Exam, UserAnswer }) {
        ExamDetail.belongsTo(Exam, {
            foreignKey: 'examId'
        });

        ExamDetail.hasMany(UserAnswer, {
            foreignKey: 'examDetailId'
        });
    }
  }
  ExamDetail.init({
    id: {
        type: DataTypes.STRING,
        primaryKey: true,
        field: 'id'
    },
    question: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'question'
    },
    answers: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'answers'
    },
    trueAnswer: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'true-answer'
    },
    examId: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'examId'
    }
  }, {
    sequelize,
    modelName: 'ExamDetail',
    tableName: 'exam-details'
  });
  return ExamDetail;
};