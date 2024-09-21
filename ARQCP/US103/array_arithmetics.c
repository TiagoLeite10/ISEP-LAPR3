short max_value_in_array(short *ptr_array, int size) {
    short max = *ptr_array;
    
    for (int pos = 0; pos < size; pos++) {
        if (max < *ptr_array)
            max = *ptr_array;

        ptr_array++;
    }

    return max;
}

short min_value_in_array(short *ptr_array, int size) {
    short min = *ptr_array;
    for (int pos = 0; pos < size; pos++) {
        if (min > *ptr_array)
            min = *ptr_array;

        ptr_array++;
    }

    return min;
}

short average_of_values_in_array(short *ptr_array, int size) {
    int sum_all_values = 0;

    for (int pos = 0; pos < size; pos++) {
        sum_all_values += (int)*ptr_array;
        ptr_array++;
    }

    return (short)(sum_all_values / size);

}