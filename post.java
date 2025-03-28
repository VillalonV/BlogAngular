@Entity
@Getter @Setter @NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String content;
    private LocalDateTime createdAt;
    
}

public interface PostRepository extends JpaRepository<Post, Long> {
}

@RestController
@RequestMapping("/api/posts")
public class BlogController {
    
    @Autowired
    private PostRepository postRepository;
    
    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }
    
}
