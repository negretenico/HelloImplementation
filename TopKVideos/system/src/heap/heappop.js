export const heappop = (heap) => {
  const n = heap.length;
  if (n === 0) return undefined;

  [heap[0], heap[n - 1]] = [heap[n - 1], heap[0]];
  const removedKey = heap.pop();

  let curr = 0;
  while (2 * curr + 1 < heap.length) {
      const leftIndex = 2 * curr + 1;
      const rightIndex = 2 * curr + 2;
      const minChildIndex = (rightIndex < heap.length && heap[rightIndex] < heap[leftIndex])
          ? rightIndex
          : leftIndex;

      if (heap[minChildIndex] < heap[curr]) {
          [heap[minChildIndex], heap[curr]] = [heap[curr], heap[minChildIndex]];
          curr = minChildIndex;
      } else {
          break;
      }
  }

  return removedKey;
};