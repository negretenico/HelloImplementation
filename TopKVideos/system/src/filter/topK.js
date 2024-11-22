import { heapify } from "../heap/heapify.js"
import {heappop} from '../heap/heappop.js'
export const topK =  (data,k)=>{
    const heap = heapify(data)
    const i = 0;
    const items = []
    for (let i = 0; i < k; i++) {
        const item = heappop(heap);
        if (item !== undefined) {
            items.push(item);
        } else {
            break; // Handle the case where k > data.length
        }
    }
    // create heap by views
    return items;
}