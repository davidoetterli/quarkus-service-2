package ch.hftm.dto;

public record BlogValidationDTO(Long postId, boolean valid) {

    public static BlogValidationDTO fromMessage(String message) {
        String[] parts = message.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid message format: " + message);
        }
        return new BlogValidationDTO(Long.parseLong(parts[0]), Boolean.parseBoolean(parts[1]));
    }

    public String toMessage() {
        return postId + ":" + valid;
    }
}
