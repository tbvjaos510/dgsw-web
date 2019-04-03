package kr.hs.dgsw.web_326.Protocol;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AttachmentProtocol {
    private String storedPath;
    private String originalName;

    public AttachmentProtocol(String storedPath, String originalName) {
        this.storedPath = storedPath;
        this.originalName = originalName;
    }
}
