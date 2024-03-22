package com.microservice.profilemicroservice.service.impl

import com.microservice.profilemicroservice.dto.ExperienceDto
import com.microservice.profilemicroservice.entity.Experience
import com.microservice.profilemicroservice.repository.ExperienceRepository
import com.microservice.profilemicroservice.service.ExperienceService
import com.microservice.profilemicroservice.util.File
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ExperienceServiceImpl(@Autowired private val experienceRepository: ExperienceRepository, @Autowired private val uploapFile: File):ExperienceService {
    override fun save(experienceDto: ExperienceDto, file: MultipartFile): Experience {
        var experienceOptional =  experienceRepository.findByName(experienceDto.name)
        val data = uploapFile.store(file)
        if(experienceOptional.isPresent){
            throw Exception("Experiencia ya existe")
        }
        var experience = Experience(name = experienceDto.name.lowercase(), date = experienceDto.date, type = experienceDto.type, data = data, email = experienceDto.email)
        return experienceRepository.save(experience)
    }

    override fun findExperience(id: Long): Experience {
        val optional: Optional<Experience> =  experienceRepository.findById(id);
        if(optional.isPresent){
            return optional.get()
        }
        throw Exception("No se encontro experiencia")
    }

    override fun list(): List<Experience> {
        return experienceRepository.findAll()
    }

    override fun listByEmail(email:String): List<Experience> {
        val experienceList = experienceRepository.findAll();
        val experienceListFilter = mutableListOf<Experience>()
        for (index in experienceList){

            if(index.email == email){
                experienceListFilter.add(index)
            }
        }
        return experienceListFilter;
    }
}