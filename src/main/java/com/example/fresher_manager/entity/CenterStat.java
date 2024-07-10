package com.example.fresher_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CenterStat extends Center{
    long fresherCount;

    public CenterStat(Long id, String name, String address, long fresherCount){
        super(id, name, address);
        this.fresherCount = fresherCount;
    }
}
