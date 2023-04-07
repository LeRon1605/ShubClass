'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Class extends Model {
    static associate({ User, StudentClass, Exam }) {
        Class.belongsTo(User, {
            foreignKey: 'teacherId'
        });

        Class.hasMany(StudentClass, {
          foreignKey: 'classId'
        });

        Class.hasMany(Exam, {
          foreignKey: 'classId'
        });
    }
  }
  Class.init({
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
    description: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'description'
    },
    subjectName: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'subject_name'
    },
    numberOfStudent: {
        type: DataTypes.INTEGER,
        allowNull: false,
        field: 'number_of_student'
    },
    teacherId: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'teacherId'
    }
  }, {
    sequelize,
    modelName: 'Class',
    tableName: 'classes'
  });
  return Class;
};