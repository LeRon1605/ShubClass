import swaggerUi from 'swagger-ui-express';
import swaggerJSDoc from 'swagger-jsdoc';

const swagger = () => {
    const swaggerDefinition = {
        info: {
            title: 'API docs',
            version: '0.1.0'
        },
        basePath: '/api',
        schemes:
            process.env.SWAGGER_SCHEMA_HTTPS === 'true'
                ? ['https']
                : ['http', 'https'],
        securityDefinitions: {
            BearerAuth: {
                type: 'apiKey',
                name: 'authorization',
                in: 'header'
            }
        },
        security: [{ BearerAuth: [] }]
    };

    const options = {
        swaggerDefinition,
        apis: ['src/api/**/*.js']
    };

    const swaggerSpec = swaggerJSDoc(options);

    const swOptions = {
        explorer: true,
        customCss:
            '.swagger-ui .opblock-body pre span {color: #DCD427 !important} .swagger-ui .opblock-body pre {color: #DCD427} .swagger-ui textarea.curl {color: #DCD427} .swagger-ui .response-col_description__inner div.markdown, .swagger-ui .response-col_description__inner div.renderedMarkdown {color: #DCD427}'
    };

    return [swaggerUi.serve, swaggerUi.setup(swaggerSpec, swOptions)];
};

export default swagger;
