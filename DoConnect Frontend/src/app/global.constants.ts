export const PRIORITY_MAPPER: Record<number, string> = {
  1: 'High',
  2: 'Medium',
  3: 'Low'
};

export const STATUS_MAPPER: Record<number, string> = {
  1: 'To-Do',
  2: 'Development',
  3: 'Testing',
  4: 'Completed'
};

export const TYPE_MAPPER: Record<number, string> = {
  1: 'BUG',
  2: 'TASK',
  3: 'STORY'
};

export const PRIORITY_OPTIONS = [
  { key: 'LOW', value: 1 },
  { key: 'MEDIUM', value: 2 },
  { key: 'HIGH', value: 3 }
];

export const STATUS_OPTIONS = [
  { key: 'To-Do', value: 1 },
  { key: 'Development', value: 2 },
  { key: 'Testing', value: 3 },
  { key: 'Completed', value: 4 }
];

export const TYPE_OPTIONS = [
  { key: 'BUG', value: 1 },
  { key: 'TASK', value: 2 },
  { key: 'STORY', value: 3 }
];
