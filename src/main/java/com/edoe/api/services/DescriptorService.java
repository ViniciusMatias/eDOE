package com.edoe.api.services;

import com.edoe.api.entity.Descriptor;
import com.edoe.api.repositories.DescriptorRepository;
import com.edoe.api.services.exceptions.RepeatedNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DescriptorService {

    @Autowired
    private DescriptorRepository descriptorRepository;


    public List<Descriptor> getDescriptors()
    {
        return descriptorRepository.findAll();
    }

    public Descriptor saveDescriptor(Descriptor descriptor) throws Exception {
        descriptor.setName(descriptor.getName().toLowerCase().trim());
            if(existDescriptor(descriptor)){
                throw new RepeatedNameException("Descritor ja existente");
            } else{
                    return descriptorRepository.save(descriptor);
                }
            }

    public boolean existDescriptor(Descriptor descriptor){

        for (String desc : descriptorRepository.findAll().stream().map((e) -> e.getName()).collect(Collectors.toList()) ) {

            if(desc.equals(descriptor.getName())) {
                return true;
            }
        }
        return false;
    }

    public Descriptor descriptorById(Long id) {
       return descriptorRepository.findById(id).get();
    }



}
