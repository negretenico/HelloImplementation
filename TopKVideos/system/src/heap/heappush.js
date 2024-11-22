export const heappush  =(heap,newKey) =>{
    heap.push(newKey);
    let curr = heap.length-1;
    // keep comparing till root is reached or we terminate in middle
     while(curr > 0){
       let parent = Math.floor((curr-1)/2)
       if( heap[curr] < heap[parent] ){
         // quick swap
         [ heap[curr], heap[parent] ] = [ heap[parent], heap[curr] ]
         // update the index of newKey
         curr = parent
       } else{
         // if no swap, break, since we heap is stable now
         break
       }
     } 
}