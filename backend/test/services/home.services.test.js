import HomeService from "../../src/api/home/home.service.js";
import { test, expect } from 'jest';

test('Say greeting', () =>{
    expect(HomeService.greeting()).toBe('Hello World');
});

