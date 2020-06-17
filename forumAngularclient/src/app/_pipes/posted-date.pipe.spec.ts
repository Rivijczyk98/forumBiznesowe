import { PostedDatePipe } from './posted-date.pipe';

describe('PostedDatePipe', () => {
  it('create an instance', () => {
    const pipe = new PostedDatePipe();
    expect(pipe).toBeTruthy();
  });
});
