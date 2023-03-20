'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Class extends Model {
    static associate({ User }) {
        Class.belongsTo(User, {
            foreignKey: 'teacherId'
        })
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