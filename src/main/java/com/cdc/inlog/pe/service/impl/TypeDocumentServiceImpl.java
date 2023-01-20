package com.cdc.inlog.pe.service.impl;

import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_TYPE_DOCUMENT;
import com.cdc.inlog.pe.entity.TypeDocumentEntity;
import com.cdc.inlog.pe.repository.TypeDocumentRepository;
import com.cdc.inlog.pe.service.TypeDocumentService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TypeDocumentServiceImpl implements TypeDocumentService {

    @Autowired
    private TypeDocumentRepository typeDocumentRepository;

    @Override
    public List<TypeDocumentEntity> getAllEntity() {
        log.info("TypeDocumentServiceImpl.getAllEntity");
        return typeDocumentRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_DOCUMENT));
    }

    @Override
    public List<TypeDocumentEntity> getAllEntityActivated() {
        log.info("TypeDocumentServiceImpl.getAllEntityActivated");
        return typeDocumentRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_DOCUMENT))
                .stream()
                .filter(TypeDocumentEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<TypeDocumentEntity> getAllEntityDeactivated() {
        log.info("TypeDocumentServiceImpl.getAllEntityDeactivated");
        return typeDocumentRepository.findAll(Sort.by(Sort.Direction.ASC, WORD_TYPE_DOCUMENT))
                .stream()
                .filter(typeDocumentEntity -> !typeDocumentEntity.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public TypeDocumentEntity getAllEntityById(Integer id) {
        log.info("TypeDocumentServiceImpl.getAllEntityById");
        log.info("TypeDocumentServiceImpl.getAllEntityById.id: " + id);
        return typeDocumentRepository.findById(id)
                .orElse(new TypeDocumentEntity());
    }

    @Override
    public TypeDocumentEntity registerEntity(TypeDocumentEntity typeDocumentEntity) {
        log.info("TypeDocumentServiceImpl.registerEntity");
        return typeDocumentRepository.save(typeDocumentEntity);
    }

    @Override
    public boolean existsEntityById(Integer id) {
        log.info("TypeDocumentServiceImpl.existsEntityById");
        log.info("TypeDocumentServiceImpl.existsEntityById.id: " + id);
        return typeDocumentRepository.existsById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer updateEntityById(TypeDocumentEntity typeDocumentEntity) {
        log.info("TypeDocumentServiceImpl.updateEntityById");
        log.info("TypeDocumentServiceImpl.updateEntityById.id: " + typeDocumentEntity.getIdTypeDocument());
        if (typeDocumentRepository.existsById(typeDocumentEntity.getIdTypeDocument())) {
            return typeDocumentRepository.updateTypeDocumentEntityAbbreviationAndTypeDocumentByIdTypeDocument(
                    typeDocumentEntity.getAbbreviation(),
                    typeDocumentEntity.getTypeDocument(),
                    typeDocumentEntity.getIdTypeDocument()) == NUMBER_ONE ? NUMBER_ONE : NUMBER_ZERO;
        } else {
            return NUMBER_TWO;
        }
    }

    @Override
    public Integer checkActiveById(Integer id) {
        log.info("TypeDocumentServiceImpl.checkActiveById");
        log.info("TypeDocumentServiceImpl.checkActiveById.id: " + id);
        if (!typeDocumentRepository.existsById(id)) {
            return NUMBER_ZERO;
        } else {
            return typeDocumentRepository.getActiveOfTypeDocumentEntityById(id) ? NUMBER_ONE : NUMBER_TWO;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean activateEntityById(Integer id) {
        log.info("TypeDocumentServiceImpl.activateEntityById");
        log.info("TypeDocumentServiceImpl.activateEntityById.id: " + id);
        return typeDocumentRepository.updateActiveOfTypeDocumentEntityById(true, id) == NUMBER_ONE;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deactivateEntityById(Integer id) {
        log.info("TypeDocumentServiceImpl.deactivateEntityById");
        log.info("TypeDocumentServiceImpl.deactivateEntityById.id: " + id);
        return typeDocumentRepository.updateActiveOfTypeDocumentEntityById(true, id) == NUMBER_ONE;
    }

}