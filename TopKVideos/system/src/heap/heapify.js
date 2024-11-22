import { heappush } from "./heappush.js"

export const heapify = (arr) =>{
    const heap = [];
    for (const item of arr) {
        heappush(heap, item);
    }
    return heap;
}